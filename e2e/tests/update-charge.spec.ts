import { test, expect } from '@playwright/test';

test('deve atualizar endereço de cobrança com sucesso', async ({ page }) => {
  // 1. Define IDs fixos para o cliente e para a cobrança
  const clienteId = '1ce568cf-c16f-4319-a05b-cd505e9bb8cf';
  const entregaId = '2469c418-6557-4f4a-9ffb-10c0e4c8b20b';

  // 2. Abre o formulário de atualização
  await page.goto(`http://127.0.0.90:5501/update-charge.html?id=${clienteId}&entregaId=${entregaId}`);

  // 3. Preenche os campos do formulário
  await page.fill('#receiver', 'Novo Recebedor');
  await page.fill('#typeResidence', 'Apartment');
  await page.fill('#streetType', 'Avenue');
  await page.fill('#street', 'Rua Francisco Gabriel Sobrinho ');
  await page.fill('#number', '456');
  await page.fill('#neighborhood', 'Jardim Residencial São José');
  await page.fill('#observation', 'Próximo ao mercado');
  await page.fill('#city', 'Rondonópolis');
  await page.fill('#state', 'MT');
  await page.fill('#country', 'Brazil');

  // 4. Captura o alert de sucesso/erro
  page.once('dialog', async dialog => {
    console.log('Mensagem do alert:', dialog.message());
    await dialog.dismiss();
  });

  // 5. Submete o formulário
  await page.click('button[type="submit"]');

  // 6. Aguarda redirecionamento para index.html ou raiz
  await page.waitForFunction(() =>
    window.location.href.endsWith('index.html') || window.location.href.endsWith('/')
  );

  // 7. Valida a URL final
  const url = page.url();
  expect(url.endsWith('index.html') || url.endsWith('/')).toBeTruthy();
});
