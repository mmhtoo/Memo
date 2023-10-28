import {api} from '@constants/routes.ts'
import axios from '@libs/axios/axiosInstance.ts'
import {AxiosError, AxiosResponse} from 'axios'

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

export const registerAccount = async (dto: RegisterAccountDto) => {
  try {
    const response: AxiosResponse<
      DataResponse<Account> &
        ErrorResponse<RegisterAccountDto | {error: string}>
    > = await axios.post(api.register, dto)
    return response.data
  } catch (e) {
    const error = e as AxiosError
    throw error
  }
}

export const loginAccount = async (dto: LoginAccountDto) => {
  const response: AxiosResponse<
    DataResponse<Account> | ErrorResponse<LoginAccountDto | {error: string}>
  > = await axios.post(api.login, dto)
  return response
}

export const verifyAccount = async (dto: VerifyAccountDto) => {
  const response: AxiosResponse<
    DataResponse<string> & ErrorResponse<{error: string}>
  > = await axios.get(
    `${api.verifyAccount}?code=${dto.code}&email=${dto.email}`
  )
  return response.data
}
