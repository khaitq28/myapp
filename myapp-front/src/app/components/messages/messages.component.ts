import {Component, OnInit, ViewChild} from '@angular/core';
import {Message} from "../../models/message.model";
import {ApiMessageService} from "../../services/api-message.service";
import {MatDialog, MatDialogModule} from "@angular/material/dialog";
import {HttpClientModule} from "@angular/common/http";
import {MatTable, MatTableDataSource, MatTableModule} from "@angular/material/table";
import {MatPaginator, MatPaginatorModule, PageEvent} from "@angular/material/paginator";
import {CommonModule, DatePipe} from "@angular/common";
import {MatButton} from "@angular/material/button";
import {MessageDetailComponent} from "../message-detail/message-detail.component";
import {MatLabel} from "@angular/material/form-field";

@Component({
  selector: 'app-messages',
  standalone: true,
  imports: [HttpClientModule, MatPaginator, MatTable, DatePipe, CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatDialogModule, MatButton, MatLabel],
  templateUrl: './messages.component.html',
  styleUrl: './messages.component.scss'
})
export class MessagesComponent implements OnInit {

  messages: Message[] = [];
  displayedColumns: string[] = ['id', 'content', 'createdAt'];

  dataSource = new MatTableDataSource<Message>();
  totalElements = 0;
  pageSize = 10;
  pageIndex = 0;

  @ViewChild(MatPaginator) paginator!: MatPaginator;


  constructor(private api:ApiMessageService,
              private dialog: MatDialog) {
  }

  ngOnInit() {
    this.loadMessages();
  }
  private loadMessages() {
    this.api.getMessages(this.pageIndex, this.pageSize).subscribe((data: any) => {
      console.log(data);
      this.messages = data.content;
      this.totalElements = data.totalElements;
    });
  }


  onPageChange(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadMessages();
  }

  goToLastPage(): void {
    const lastPage = Math.ceil(this.totalElements / this.pageSize) - 1;
    this.pageIndex = lastPage >= 0 ? lastPage : 0;
    this.paginator.pageIndex = this.pageIndex;
    this.loadMessages();
  }

  goToPage() {
    const lastPage = Math.ceil(this.totalElements / this.pageSize) - 1;
    this.pageIndex = lastPage >= 0 ? lastPage : 0;
    this.loadMessages();
  }

  goToFirstPage(): void {
    this.pageIndex = 0;
    this.paginator.pageIndex = 0;
    this.loadMessages();
  }

  openMessageDetail(message:Message) {
    this.dialog.open(MessageDetailComponent, {
      width: '400px',
      data: message
    });
  }
}
