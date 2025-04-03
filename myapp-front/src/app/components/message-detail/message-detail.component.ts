import {Component, Inject, OnInit} from '@angular/core';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogTitle
} from "@angular/material/dialog";
import {Message} from "../../models/message.model";
import {DatePipe} from "@angular/common";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'app-message-detail',
  standalone: true,
  imports: [
    DatePipe,
    MatDialogActions,
    MatDialogContent,
    MatDialogClose,
    MatButton,
    MatDialogTitle
  ],
  templateUrl: './message-detail.component.html',
  styleUrl: './message-detail.component.scss'
})
export class MessageDetailComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public message: Message) {}


}
