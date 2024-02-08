import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-students-form',
  templateUrl: './students-form.component.html',
  styleUrls: ['./students-form.component.css']
})
export class StudentsFormComponent {

  name: string = '';
  searchName: string = '';
  grade: string = '';
  successfulText: any;
  allGrades: any;
  finalGrade: any;

  constructor(private http: HttpClient) { }

  private getAuthHeader(username: string, password: string): HttpHeaders {
    const credentials = btoa(`${username}:${password}`);
    return new HttpHeaders({
      'Authorization': `Basic ${credentials}`,
      'Content-Type': 'application/json'
    });
  }

  submitForm() {
    const url = `http://localhost:8081/api/submit?name=${encodeURIComponent(this.name)}&grade=${encodeURIComponent(this.grade)}`;
    const headers = this.getAuthHeader("website", "password");

    this.http.get(url, { headers }).subscribe(
      (data) => {
        // Process the response data here
        console.log(data);
        this.successfulText = "Successfully sent to the organizer service!";
      },
      (error) => {
        // Handle errors here
        console.error('Error:', error);
      }
    );
  }

  search() {
    const url = `http://localhost:8082/api/search?name=${encodeURIComponent(this.searchName)}`;
    const headers = this.getAuthHeader("website", "password");

    this.http.get(url, { headers }).subscribe(
      (data) => {
        // Process the response data here
        console.log(data);
        this.allGrades = data;
      },
      (error) => {
        // Handle errors here
        console.error('Error:', error);
      }
    );
  }

  calculate() {
    const url = `http://localhost:8082/api/calculate?name=${encodeURIComponent(this.searchName)}`;
    const headers = this.getAuthHeader("website", "password");

    this.http.get(url, { headers }).subscribe(
      (data) => {
        // Process the response data here
        console.log(data);
        this.finalGrade = data;
      },
      (error) => {
        // Handle errors here
        console.error('Error:', error);
      }
    );
  }

}
