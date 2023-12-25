export interface Todo {
  id?: number;
  title: string;
  description: string;
  completed: boolean;
  status: Status;
}

export enum Status {

  PENDING,PROGRESS, COMPLETED
}
