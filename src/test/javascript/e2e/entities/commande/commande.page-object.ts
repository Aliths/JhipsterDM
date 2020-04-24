import { element, by, ElementFinder } from 'protractor';

export class CommandeComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-commande div table .btn-danger'));
  title = element.all(by.css('jhi-commande div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class CommandeUpdatePage {
  pageTitle = element(by.id('jhi-commande-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  echeanceInput = element(by.id('field_echeance'));

  userSelect = element(by.id('field_user'));
  restaurantSelect = element(by.id('field_restaurant'));
  panierSelect = element(by.id('field_panier'));
  paiementSelect = element(by.id('field_paiement'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setEcheanceInput(echeance: string): Promise<void> {
    await this.echeanceInput.sendKeys(echeance);
  }

  async getEcheanceInput(): Promise<string> {
    return await this.echeanceInput.getAttribute('value');
  }

  async userSelectLastOption(): Promise<void> {
    await this.userSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async userSelectOption(option: string): Promise<void> {
    await this.userSelect.sendKeys(option);
  }

  getUserSelect(): ElementFinder {
    return this.userSelect;
  }

  async getUserSelectedOption(): Promise<string> {
    return await this.userSelect.element(by.css('option:checked')).getText();
  }

  async restaurantSelectLastOption(): Promise<void> {
    await this.restaurantSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async restaurantSelectOption(option: string): Promise<void> {
    await this.restaurantSelect.sendKeys(option);
  }

  getRestaurantSelect(): ElementFinder {
    return this.restaurantSelect;
  }

  async getRestaurantSelectedOption(): Promise<string> {
    return await this.restaurantSelect.element(by.css('option:checked')).getText();
  }

  async panierSelectLastOption(): Promise<void> {
    await this.panierSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async panierSelectOption(option: string): Promise<void> {
    await this.panierSelect.sendKeys(option);
  }

  getPanierSelect(): ElementFinder {
    return this.panierSelect;
  }

  async getPanierSelectedOption(): Promise<string> {
    return await this.panierSelect.element(by.css('option:checked')).getText();
  }

  async paiementSelectLastOption(): Promise<void> {
    await this.paiementSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async paiementSelectOption(option: string): Promise<void> {
    await this.paiementSelect.sendKeys(option);
  }

  getPaiementSelect(): ElementFinder {
    return this.paiementSelect;
  }

  async getPaiementSelectedOption(): Promise<string> {
    return await this.paiementSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class CommandeDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-commande-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-commande'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
