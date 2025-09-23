import { test, expect } from '@playwright/test';

test('deve registrar novo cliente com email único', async ({ page }) => {
  // 1. Abre o formulário servido pelo Live Server
  await page.goto('http://127.0.0.90:5501/create-customer.html');

  // 2. Gera email único usando timestamp
  const timestamp = Date.now();
  const email = `kleberson${timestamp}@example.com`;

  // 3. Preenche dados pessoais
  await page.fill('#name', 'Kleberson dos Santos');
  await page.selectOption('#gender', 'MALE');
  await page.fill('#birth', '2000-01-01');
  await page.fill('#cpf', '123.456.789-04');

  // 4. Preenche contato
  await page.fill('#email', email);
  await page.fill('#ddd', '11');
  await page.fill('#phone', '987654321');
  await page.selectOption('#typePhone', 'MOBILE');

  // 5. Preenche senha
  await page.fill('#password', 'Senha@123');
  await page.fill('#confirmPassword', 'Senha@123');

  // 6. Captura alerta caso haja erro ou sucesso
  page.once('dialog', async dialog => {
    console.log('Mensagem do alert:', dialog.message());
    await dialog.dismiss();
  });

  // 7. Clica no botão de enviar
  await page.click('#submitBtn');

  // 8. Espera até que a URL contenha 'index.html' ou termine em '/'
  await page.waitForFunction(() =>
    window.location.href.endsWith('index.html') || window.location.href.endsWith('/')
  );

  // 9. Valida a URL final (compatível com todos os navegadores)
  const url = page.url();
  expect(url.endsWith('index.html') || url.endsWith('/')).toBeTruthy();
});