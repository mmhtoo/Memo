import {api} from '@constants/routes.ts'
import axios from '@libs/axios/axiosInstance.ts'

export const getAccountInfo = async (
  accountId: string
): Promise<DataResponse<Account> | ErrorResponse<{error: string}>> => {
  const response = await axios.get(
    api.account.replace('{accountId}', accountId)
  )
  return response.data
}
