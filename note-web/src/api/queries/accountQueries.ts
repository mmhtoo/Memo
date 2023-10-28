import {api} from '@constants/routes.ts'
import axios from '@libs/axios/axiosInstance.ts'
import {AxiosError, AxiosResponse} from 'axios'

export const getAccountInfo = async (accountId: string) => {
  try {
    const response: AxiosResponse<
      DataResponse<Account> | ErrorResponse<{error: string}>
    > = await axios.get(api.account.replace('{accountId}', accountId))
    return response.data
  } catch (e) {
    const error = e as AxiosError
    throw error
  }
}
