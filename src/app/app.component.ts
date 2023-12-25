import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';

import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatIconModule } from '@angular/material/icon';

import { Status, Todo } from './models/todo';
import { HttpService } from './services/http.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    MatIconModule,
    MatInputModule,
    HttpClientModule,
    MatSelectModule,
  ],
  providers: [HttpClientModule, HttpClient],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  todoForm!: FormGroup;
  todos: Todo[] = [];
  isEditMode: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private httpService: HttpService
  ) {
    this.getTodos();
    this.todoForm = this.formBuilder.group({
      id: [''],
      title: ['', Validators.required],
      description: [''],
      completed: [''],
      // status: ['' , Validators.required],

    });
  }

  ngOnInit() {
    this.getTodos();

  }

  editTodo(todo: Todo) {
    
    this.todoForm.setValue({
      id: todo.id,
      title: todo.title,
      description: todo.description,
      completed: todo.completed,
      // status: todo.status,
    });

    console.log("fun: editTodo"+todo);
  }

  getTodos() {
    this.httpService.getTodos().subscribe((data: Todo[]) => {
      this.todos = data;
    });
  }

  updateTodo(todo: Todo) {
    this.httpService.updateTodo(todo).subscribe((data) => {
      this.getTodos();
      this.todoForm.reset();
    });
  }

  deleteTodo(id: number) {
    this.httpService.deleteTodo(id).subscribe(() => {
      this.getTodos();
    });
  }

  handleEdit(todo: Todo) {
    console.log("fun: handleTodo"+todo);
    this.isEditMode = true;
    this.todoForm.setValue(todo);
  }

  onSubmit() {
    if (this.todoForm.invalid) {
      return;
    }
    const formValue: Todo = this.todoForm.value;
    console.log(formValue);
    if (this.isEditMode) {
      this.updateTodo(formValue);
      this.isEditMode = false;
    } else {
      const todoRequest: Todo = {
        title: formValue.title,
        description: formValue.description,
        completed: false,
        status: Status.PENDING,
      };
      this.httpService.createTodo(todoRequest).subscribe((data) => {
        this.getTodos();
      });
    }
  }
}
