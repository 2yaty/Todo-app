import { Todo } from "../models/todo";

export interface TodoGetResponse {
    code: string;
    successMessage: string;
    time:Date;
    data:{
        data:Todo[];
    }

}
