import {API_PREFIX, REQUEST_TIMEOUT} from '@constants/environmentVariables.ts'
import axios from 'axios'
import tokenInterceptor from './interceptors/tokenInterceptor.ts'
import accountIdInterceptor from './interceptors/accountIdInteceptor.ts'

const axiosInstance = axios.create({
  baseURL: API_PREFIX,
  timeout: REQUEST_TIMEOUT,
})

axiosInstance.interceptors.request.use(accountIdInterceptor)
axiosInstance.interceptors.request.use(tokenInterceptor)

export default axiosInstance
