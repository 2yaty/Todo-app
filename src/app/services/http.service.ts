import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Todo } from '../models/todo';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { TodoGetResponse } from './TodoGetResponse';

@Injectable({
  providedIn: 'root',
})
export class HttpService {
  todoApi = 'http://localhost:8082/todo';

  constructor(private httpClient: HttpClient) {}

  createTodo(todo: Todo): Observable<Todo> {
    return this.httpClient.post<Todo>(`${this.todoApi}/add?userId=1`, todo);
  }


  getTodos(): Observable<Todo[]> {
    return this.httpClient.get<TodoGetResponse>(`${this.todoApi}/all?userId=1`).pipe(
      map((response) => {
        return response.data.data;
      })
    );
  }
  updateTodo(todo: Todo): Observable<Todo> {
    const updateUrl = `${this.todoApi}/?userId=1&todoId=${todo.id}`;
    return this.httpClient.put<Todo>(updateUrl, todo);
  }

  deleteTodo(id: number): Observable<Todo> {
    const deleteUrl = `${this.todoApi}?todoId=${id}`;
    return this.httpClient.delete<Todo>(deleteUrl);
  }
}
