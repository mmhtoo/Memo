import {api} from '@constants/routes.ts'
import axiosInstance from '@libs/axios/axiosInstance.ts'
import {AxiosError, AxiosResponse} from 'axios'

type CreateDirectoryRequestDto = {
  name: string
  description?: string
  parentDirId?: string
}

export const createNewDirectory = async (param: CreateDirectoryRequestDto) => {
  try {
    const response: AxiosResponse<DataResponse<Directory>> =
      await axiosInstance.post(api.directories, param)
    return response.data
  } catch (e) {
    const error = e as AxiosError
    throw error
  }
}
