import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class UploadService {

  constructor(private http: HttpClient) { }

  uploadFile(file: File) {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);

    return this.http.post('http://localhost:8080/api/v1/user/upload', formData);
  }
}
