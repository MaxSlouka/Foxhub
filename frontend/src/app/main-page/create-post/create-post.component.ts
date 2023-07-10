import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CreatePostPayload} from "./create-post.payload";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  createPostForm: FormGroup;
  postPayload: CreatePostPayload;

  constructor(private router: Router) {
  }

  ngOnInit() {
    this.createPostForm = new FormGroup({
      heading: new FormControl('', Validators.required),
      content: new FormControl('', Validators.required)
    });
  }

  createPost() {
    this.postPayload.
  }
}
