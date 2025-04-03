import {Component, TemplateRef, ViewChild} from '@angular/core';
import {
  MatTableDataSource, MatTableModule
} from "@angular/material/table";
import {MatPaginator, MatPaginatorModule, PageEvent} from "@angular/material/paginator";
import {MatDialog, MatDialogActions, MatDialogContent, MatDialogTitle} from "@angular/material/dialog";
import {Partner} from "../../models/partner.model";
import {ApiPartnerService} from "../../services/api-partner.service";
import {CommonModule} from "@angular/common";
import {MatButtonModule} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {MatError, MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption} from "@angular/material/autocomplete";
import {MatSelect} from "@angular/material/select";
import {MatInput} from "@angular/material/input";
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-partners',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatButtonModule,
    MatIcon,
    MatLabel,
    MatDialogContent,
    MatDialogActions,
    MatDialogTitle,
    MatOption,
    MatFormField,
    MatSelect,
    MatInput,
    MatError,
    ReactiveFormsModule
  ],
  templateUrl: './partners.component.html',
  styleUrl: './partners.component.scss'
})
export class PartnersComponent {

  partners: Partner[] = [];
  displayedColumns: string[] = [ 'alias', 'type', 'direction', 'application',  'processedFlowType', 'description', 'actions'];

  dataSource = new MatTableDataSource<Partner>();
  totalElements = 0;
  pageSize = 10;
  pageIndex = 0;

  @ViewChild('addPartnerDialog') addPartnerDialog!: TemplateRef<any>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  addPartnerForm!: FormGroup;

  constructor(private api: ApiPartnerService,
              private fb: FormBuilder,
              private dialog: MatDialog) {
  }

  ngOnInit() {
    this.addPartnerForm = this.fb.group({
      alias: ['', Validators.required],
      type: ['', Validators.required],
      direction: ['INBOUND'],
      application: [''],
      processedFlowType: ['MESSAGE'],
      description: ['', Validators.required]
    });

    this.loadPartners();
  }
  private loadPartners() {
    this.api.getPartners(this.pageIndex, this.pageSize).subscribe((data: any) => {
      console.log(data);
      this.partners = data.content;
      this.totalElements = data.totalElements;
    });
  }


  onPageChange(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadPartners();
  }

  goToLastPage(): void {
    const lastPage = Math.ceil(this.totalElements / this.pageSize) - 1;
    this.pageIndex = lastPage >= 0 ? lastPage : 0;
    this.paginator.pageIndex = this.pageIndex;
    this.loadPartners();
  }

  goToFirstPage(): void {
    this.pageIndex = 0;
    this.paginator.pageIndex = 0;
    this.loadPartners();
  }

  deletePartner(id:number) {
    if (confirm('You want to delete it?')) {
      this.api.deletePartner(id).subscribe(() => {
        this.loadPartners();
      });
    }
  }

  openAddDialog() {
    this.dialog.open(this.addPartnerDialog);
  }

  savePartner() {
    if (this.addPartnerForm.invalid) {
      this.addPartnerForm.markAllAsTouched();
      return;
    }
    const newPartner: Partner = this.addPartnerForm.value;
    this.api.savePartner(newPartner).subscribe(() => {
      this.loadPartners();
      this.dialog.closeAll();
      this.addPartnerForm.reset();
    });
  }
}
