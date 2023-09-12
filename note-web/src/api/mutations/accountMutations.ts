import {api} from '@constants/routes.ts'
import axios from '@libs/axios/axiosInstance.ts'

type RegisterAccountDto = {
  username: string
  email: string
  password: string
}
type LoginAccountDto = {
  email: string
  password: string
}

export const registerAccount = async (
  dto: RegisterAccountDto
): Promise<
  DataResponse<Account> & ErrorResponse<RegisterAccountDto | {error: string}>
> => {
  const response = await axios.post(api.register, dto)
  return response.data
}

export const loginAccount = async (
  dto: LoginAccountDto
): Promise<
  DataResponse<Account> | ErrorResponse<LoginAccountDto | {error: string}>
> => {
  const response = await axios.post(api.login, dto)
  return response.data
}
