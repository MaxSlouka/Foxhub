import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CreatePostPayload} from "./create-post.payload";
import {PostService} from "../post.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  createPostForm: FormGroup;
  postPayload: CreatePostPayload;

  constructor(private router: Router, private postService: PostService) {
    this.postPayload = {
      title: '',
      url: '',
      content: ''
    }
  }

  ngOnInit() {
    this.createPostForm = new FormGroup({
      title: new FormControl('', Validators.required),
      url: new FormControl('', Validators.required),
      content: new FormControl('', Validators.required)
    });
  }

  createPost() {
    this.postPayload.title = this.createPostForm.get('title')?.value;
    this.postPayload.url = this.createPostForm.get('url')?.value;
    this.postPayload.content = this.createPostForm.get('content')?.value;

    this.postService.createPost(this.postPayload).subscribe((data) => {
      this.router.navigateByUrl('/api/v1/posts');
    })
  }
}
