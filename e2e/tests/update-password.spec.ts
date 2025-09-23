import { test, expect } from '@playwright/test';

test('deve atualizar a senha do cliente sem token', async ({ page }) => {
  // 1. ID do cliente presente na URL
  const clientId = '1ce568cf-c16f-4319-a05b-cd505e9bb8cf';

  // 2. Abre o formulário com query param ?id={clientId}
  await page.goto(`http://127.0.0.90:5501/update-password.html?id=${clientId}`);

  // 3. Espera o formulário estar disponível
  await page.waitForSelector('#password');

  // 4. Preenche as senhas
  await page.fill('#password', 'NovaSenha@123');
  await page.fill('#confirmPassword', 'NovaSenha@123');

  // 5. Captura alerta de sucesso ou erro
  page.once('dialog', async dialog => {
    console.log('Mensagem do alert:', dialog.message());
    await dialog.dismiss();
  });

  // 6. Clica no botão de salvar
  await page.click('button[type="submit"]');

  // 7. Espera até que a URL contenha 'index.html' ou termine em '/'
  await page.waitForFunction(() =>
    window.location.href.endsWith('index.html') || window.location.href.endsWith('/')
  );

  // 8. Valida URL final
  const url = page.url();
  expect(url.endsWith('index.html') || url.endsWith('/')).toBeTruthy();
});