import store from '@store/index.ts'
import {InternalAxiosRequestConfig} from 'axios'

const tokenInterceptor = (
  config: InternalAxiosRequestConfig<any>
): InternalAxiosRequestConfig<any> => {
  const savedToken = store.getState().userReducer.token

  if (savedToken) {
    config.headers['Authorization'] = `Bearer ${savedToken}`
  }

  return config
}

export default tokenInterceptor
