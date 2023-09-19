import routes from '@constants/routes.ts'
import {useAppSelector} from '@hooks/useRedux.ts'
import {selectedToken} from '@slices/userSlice.ts'
import jwtDecode from 'jwt-decode'
import {FC, Fragment, ReactNode} from 'react'
import {Navigate} from 'react-router-dom'
import {toast} from 'react-toastify'

type Props = {
  element: ReactNode
}

type AuthData = {
  username: string
  userId: string
  email: string
  iss: string
  exp: number
}

const AuthRoute: FC<Props> = ({element}) => {
  const token = useAppSelector(selectedToken)

  const isExpire = (exp: number) =>
    Math.round(new Date().getTime() / 1000) > exp

  const NavigateToLogin = () => {
    return <Navigate to={routes.login} replace={true} />
  }

  if (token == null) {
    toast.error('Please login to continue!')
    return NavigateToLogin()
  }

  const decodedToken = jwtDecode<AuthData>(token!)

  if (isExpire(decodedToken.exp)) {
    toast.error('Please login to continue!')
    return NavigateToLogin()
  }

  return <Fragment>{element}</Fragment>
}

export default AuthRoute
