import { element, by, ElementFinder } from 'protractor';

export class ProduitComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-produit div table .btn-danger'));
  title = element.all(by.css('jhi-produit div h2#page-heading span')).first();
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

export class ProduitUpdatePage {
  pageTitle = element(by.id('jhi-produit-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  prixInput = element(by.id('field_prix'));
  dispoInput = element(by.id('field_dispo'));
  nomInput = element(by.id('field_nom'));

  restaurantSelect = element(by.id('field_restaurant'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setPrixInput(prix: string): Promise<void> {
    await this.prixInput.sendKeys(prix);
  }

  async getPrixInput(): Promise<string> {
    return await this.prixInput.getAttribute('value');
  }

  async setDispoInput(dispo: string): Promise<void> {
    await this.dispoInput.sendKeys(dispo);
  }

  async getDispoInput(): Promise<string> {
    return await this.dispoInput.getAttribute('value');
  }

  async setNomInput(nom: string): Promise<void> {
    await this.nomInput.sendKeys(nom);
  }

  async getNomInput(): Promise<string> {
    return await this.nomInput.getAttribute('value');
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

export class ProduitDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-produit-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-produit'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
