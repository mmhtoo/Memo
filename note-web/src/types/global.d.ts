import {HttpStatusCode} from 'axios'

export {}

declare global {
  interface ApiResponse {
    timestamp: Date | string
    status: HttpStatusCode
    statusCode: number
    description: string
  }
  interface DataResponse<T> extends ApiResponse {
    data: T
  }
  interface ErrorResponse<T> extends ApiResponse {
    errors: T
  }
  interface Account {
    accountId: string
    username: string
    email: string
    joinedDate: string | Date
    updatedDate?: string | Date
    isLocked: boolean
  }
  interface Directory {
    directoryId: string
    name: string
    description: string
    numberOfNotes: number
    parentDirId: string
    createdDate: Date | string
    updatedDate: Date | string
  }
  interface Note {
    noteId: number
    name: string
    title: string
    content: string
    createdDate: Date | string
    updatedDate: Date | string
  }
}
