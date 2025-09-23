import { test, expect } from '@playwright/test';

test('deleta delivery e charge automaticamente', async ({ page }) => {
  const customerId = '1ce568cf-c16f-4319-a05b-cd505e9bb8cf';

  // Aceita qualquer confirm automaticamente
  page.on('dialog', async dialog => {
    console.log('Confirm detectado:', dialog.message());
    await dialog.accept(); // aceita o confirm
  });

  // --- Deletar delivery ---
  await page.goto(`http://127.0.0.90:5501/list-deliveries.html?id=${customerId}`);
  
  // Espera cards carregarem
  await page.waitForSelector('.card');

  const initialDeliveryCards = await page.locator('.card >> text=Delete').count();
  if (initialDeliveryCards > 0) {
    await page.locator('.card >> text=Delete').first().click();
    await page.waitForTimeout(1000); // espera lista atualizar
  }
  const remainingDeliveryCards = await page.locator('.card >> text=Delete').count();
  expect(remainingDeliveryCards).toBeLessThan(initialDeliveryCards);
  console.log('Delivery deletado com sucesso!');

  // --- Deletar charge ---
  await page.goto(`http://127.0.0.90:5501/list-charges.html?id=${customerId}`);

  await page.waitForSelector('.card');

  const initialChargeCards = await page.locator('.card >> text=Delete').count();
  if (initialChargeCards > 0) {
    await page.locator('.card >> text=Delete').first().click();
    await page.waitForTimeout(1000); // espera lista atualizar
  }
  const remainingChargeCards = await page.locator('.card >> text=Delete').count();
  expect(remainingChargeCards).toBeLessThan(initialChargeCards);
  console.log('Charge deletado com sucesso!');
});
