import { SimilarityPCService } from './../../services/similarity-pc.service';
import {
  IPC,
  emptyPC,
  IPCDescription,
  emptyPCDescription,
} from './../../model/pc';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-similarity-pc',
  templateUrl: './similarity-pc.component.html',
  styleUrls: ['./similarity-pc.component.css'],
})
export class SimilarityPCComponent implements OnInit {
  myPC: IPCDescription = emptyPCDescription;
  recommendedPCs: IPC[] = [emptyPC, emptyPC];
  constructor(private similarityService: SimilarityPCService) {}

  ngOnInit(): void {}

  search() {}
}
