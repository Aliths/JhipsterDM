import { element, by, ElementFinder } from 'protractor';

export class PanierComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-panier div table .btn-danger'));
  title = element.all(by.css('jhi-panier div h2#page-heading span')).first();
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

export class PanierUpdatePage {
  pageTitle = element(by.id('jhi-panier-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  etatSelect = element(by.id('field_etat'));

  produitSelect = element(by.id('field_produit'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setEtatSelect(etat: string): Promise<void> {
    await this.etatSelect.sendKeys(etat);
  }

  async getEtatSelect(): Promise<string> {
    return await this.etatSelect.element(by.css('option:checked')).getText();
  }

  async etatSelectLastOption(): Promise<void> {
    await this.etatSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async produitSelectLastOption(): Promise<void> {
    await this.produitSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async produitSelectOption(option: string): Promise<void> {
    await this.produitSelect.sendKeys(option);
  }

  getProduitSelect(): ElementFinder {
    return this.produitSelect;
  }

  async getProduitSelectedOption(): Promise<string> {
    return await this.produitSelect.element(by.css('option:checked')).getText();
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

export class PanierDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-panier-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-panier'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
