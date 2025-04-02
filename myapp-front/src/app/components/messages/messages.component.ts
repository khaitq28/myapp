import {Component, OnInit, ViewChild} from '@angular/core';
import {Message} from "../../models/message.model";
import {ApiService} from "../../services/api.service";
import {MatDialog, MatDialogModule} from "@angular/material/dialog";
import {HttpClientModule} from "@angular/common/http";
import {MatTable, MatTableDataSource, MatTableModule} from "@angular/material/table";
import {MatPaginator, MatPaginatorModule} from "@angular/material/paginator";
import {CommonModule, DatePipe} from "@angular/common";

@Component({
  selector: 'app-messages',
  standalone: true,
  imports: [HttpClientModule, MatPaginator, MatTable, DatePipe,CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatDialogModule],
  templateUrl: './messages.component.html',
  styleUrl: './messages.component.scss'
})
export class MessagesComponent implements OnInit {

  messages: Message[] = [];
  displayedColumns: string[] = ['id', 'content', 'createdAt'];

  dataSource = new MatTableDataSource<Message>();
  totalElements = 0;
  pageSize = 10;
  @ViewChild(MatPaginator) paginator!: MatPaginator;


  constructor(private api:ApiService,
              private dialog: MatDialog) {
  }

  ngOnInit() {
    this.loadMessage();
  }
  private loadMessage() {
    this.api.getMessages().subscribe((messages: Message[]) => {
      console.log(messages);
      this.messages = messages;
    });
  }


}
