import { test, expect } from '@playwright/test';

test('deve registrar novo endereço de entrega', async ({ page }) => {
  // 1. Define o ID do cliente e abre o formulário
  const customerId = '1ce568cf-c16f-4319-a05b-cd505e9bb8cf';
  await page.goto(`http://127.0.0.90:5501/create-delivery.html?id=${customerId}`);

  // 2. Preenche os dados do endereço
  await page.fill('#receiver', 'Kleberson dos Santos');
  await page.fill('#zipCode', '12345678');
  await page.fill('#typeResidence', 'House');
  await page.fill('#streetType', 'Street');
  await page.fill('#street', 'Flower Street');
  await page.fill('#number', '123');
  await page.fill('#neighborhood', 'Central');
  await page.fill('#observation', 'Perto da padaria');
  await page.fill('#city', 'Mogi das Cruzes');
  await page.fill('#state', 'SP');
  await page.fill('#country', 'Brazil');
  await page.fill('#deliveryPhrase', 'Deixar na portaria');

  // 3. Captura alerta de sucesso ou erro
  page.once('dialog', async dialog => {
    console.log('Mensagem do alert:', dialog.message());
    await dialog.dismiss();
  });

  // 4. Clica no botão de salvar
  await page.click('button[type="submit"]');

  // 5. Aguarda redirecionamento para index.html ou raiz
  await page.waitForFunction(() =>
    window.location.href.endsWith('index.html') || window.location.href.endsWith('/')
  );

  // 6. Valida a URL final
  const url = page.url();
  expect(url.endsWith('index.html') || url.endsWith('/')).toBeTruthy();
});
