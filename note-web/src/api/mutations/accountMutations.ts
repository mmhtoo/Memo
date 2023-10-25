import {api} from '@constants/routes.ts'
import axios from '@libs/axios/axiosInstance.ts'
import {AxiosResponse} from 'axios'

type RegisterAccountDto = {
  username: string
  email: string
  password: string
}
type LoginAccountDto = {
  email: string
  password: string
}

type VerifyAccountDto = {
  code: string
  email: string
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
) => {
  const response :  AxiosResponse<
  DataResponse<Account> | ErrorResponse<LoginAccountDto | {error: string}>
> = await axios.post(api.login, dto)
  return response
}

export const verifyAccount = async (
  dto: VerifyAccountDto
): Promise<DataResponse<string> & ErrorResponse<{error: string}>> => {
  const response = await axios.get(
    `${api.verifyAccount}?code=${dto.code}&email=${dto.email}`
  )
  return response.data
}
