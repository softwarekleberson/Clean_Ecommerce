import { test, expect } from '@playwright/test';

test('deve atualizar endereço de entrega existente', async ({ page }) => {
  // 1. Define IDs do cliente e da entrega e abre o formulário
  const customerId = '1ce568cf-c16f-4319-a05b-cd505e9bb8cf';
  const deliveryId = 'd2db875a-c2fa-4781-8b01-6b0382b84d70';
  await page.goto(`http://127.0.0.90:5501/update-delivery.html?id=${customerId}&entregaId=${deliveryId}`);

  // 2. Preenche os dados do endereço
  await page.fill('#receiver', 'João da Silva');
  await page.fill('#typeResidence', 'Apartment');
  await page.fill('#streetType', 'Avenue');
  await page.fill('#street', 'Paulista');
  await page.fill('#number', '1000');
  await page.fill('#neighborhood', 'Bela Vista');
  await page.fill('#observation', 'Entregar na portaria');
  await page.fill('#city', 'São Paulo');
  await page.fill('#state', 'SP');
  await page.fill('#deliveryPhrase', 'Deixar na caixa de correio');
  await page.fill('#country', 'Brazil');

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
