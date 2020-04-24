import { element, by, ElementFinder } from 'protractor';

export class PaiementComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-paiement div table .btn-danger'));
  title = element.all(by.css('jhi-paiement div h2#page-heading span')).first();
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

export class PaiementUpdatePage {
  pageTitle = element(by.id('jhi-paiement-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  methodeSelect = element(by.id('field_methode'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setMethodeSelect(methode: string): Promise<void> {
    await this.methodeSelect.sendKeys(methode);
  }

  async getMethodeSelect(): Promise<string> {
    return await this.methodeSelect.element(by.css('option:checked')).getText();
  }

  async methodeSelectLastOption(): Promise<void> {
    await this.methodeSelect
      .all(by.tagName('option'))
      .last()
      .click();
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

export class PaiementDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-paiement-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-paiement'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
