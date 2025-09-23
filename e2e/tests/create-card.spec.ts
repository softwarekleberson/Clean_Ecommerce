import { test, expect } from '@playwright/test';

test('deve registrar novo cartão para cliente existente', async ({ page }) => {
  // 1. Define o ID do cliente e abre o formulário
  const customerId = '1ce568cf-c16f-4319-a05b-cd505e9bb8cf';
  await page.goto(`http://127.0.0.90:5501/create-card.html?id=${customerId}`);

  // 2. Preenche os dados do cartão
  await page.check('#main'); // marca como principal
  await page.fill('#printedName', 'Kleberson dos Santos Silva');
  await page.fill('#numberCard', '3574487357752566');
  await page.fill('#code', '123');
  await page.fill('#expirationDate', '2030-12-31');
  await page.selectOption('#flag', 'VISA');

  // 3. Captura alerta de sucesso ou erro
  page.once('dialog', async dialog => {
    console.log('Mensagem do alert:', dialog.message());
    await dialog.dismiss(); // fecha o alert
  });

  // 4. Clica no botão de enviar
  await page.click('button[type="submit"]');

  // 5. Aguarda redirecionamento para index.html ou raiz
  await page.waitForFunction(() =>
    window.location.href.endsWith('index.html') || window.location.href.endsWith('/')
  );

  // 6. Valida a URL final
  const url = page.url();
  expect(url.endsWith('index.html') || url.endsWith('/')).toBeTruthy();
});
