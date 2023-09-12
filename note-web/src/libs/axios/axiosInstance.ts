import {API_PREFIX, REQUEST_TIMEOUT} from '@constants/environmentVariables.ts'
import axios from 'axios'

const axiosInstance = axios.create({
  baseURL: API_PREFIX,
  timeout: REQUEST_TIMEOUT,
})

export default axiosInstance
