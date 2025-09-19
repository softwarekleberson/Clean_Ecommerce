import { test, expect } from '@playwright/test';

test('deve atualizar dados de cliente existente sem token', async ({ page }) => {
  // 1. Abre o formulário de update com query param ?id={clientId}
  const clientId = '449b5c47-93b9-451e-b9f8-cf1f716c54b2';
  await page.goto(`http://127.0.0.90:5501/update-customer.html?id=${clientId}`);

  // 2. Espera o formulário ser preenchido pelo GET
  await page.waitForSelector('#name');

  // 3. Atualiza os campos
  await page.fill('#name', 'Kleberson dos Santos Atualizado');
  await page.fill('#birth', '2000-12-12');
  await page.fill('#ddd', '11');
  await page.fill('#phone', '999888777');
  await page.selectOption('#typePhone', 'MOBILE');

  // 4. Captura alerta de sucesso
  page.once('dialog', async dialog => {
    console.log('Mensagem do alert:', dialog.message());
    await dialog.dismiss();
  });

  // 5. Clica no botão de enviar
  await page.click('button[type="submit"]');

  // 6. Espera até que a URL contenha 'index.html' ou termine em '/'
  await page.waitForFunction(() =>
    window.location.href.endsWith('index.html') || window.location.href.endsWith('/')
  );

  // 7. Valida a URL final
  const url = page.url();
  expect(url.endsWith('index.html') || url.endsWith('/')).toBeTruthy();
});
