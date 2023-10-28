import store from '@store/index.ts'
import {InternalAxiosRequestConfig} from 'axios'

const accountIdInterceptor = (config: InternalAxiosRequestConfig<any>) => {
  const accountId = store.getState().userReducer.userId

  if (accountId && config.data) config.data['accountId'] = accountId

  return config
}

export default accountIdInterceptor
