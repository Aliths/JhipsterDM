import { element, by, ElementFinder } from 'protractor';

export class CooperativeComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-cooperative div table .btn-danger'));
  title = element.all(by.css('jhi-cooperative div h2#page-heading span')).first();
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

export class CooperativeUpdatePage {
  pageTitle = element(by.id('jhi-cooperative-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nomInput = element(by.id('field_nom'));

  userSelect = element(by.id('field_user'));
  restaurantSelect = element(by.id('field_restaurant'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNomInput(nom: string): Promise<void> {
    await this.nomInput.sendKeys(nom);
  }

  async getNomInput(): Promise<string> {
    return await this.nomInput.getAttribute('value');
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

export class CooperativeDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-cooperative-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-cooperative'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
