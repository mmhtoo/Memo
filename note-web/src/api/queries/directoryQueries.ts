import {api} from '@constants/routes.ts'
import axiosInstance from '@libs/axios/axiosInstance.ts'
import {AxiosError, AxiosResponse} from 'axios'

export const getDirectoriesForUser = async () => {
  try {
    const response: AxiosResponse<
      DataResponse<Directory[]>,
      ErrorResponse<{error: string}>
    > = await axiosInstance.get(api.directories)
    return response.data
  } catch (e) {
    const error = e as AxiosError
    throw error
  }
}
