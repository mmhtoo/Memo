import routes from '@constants/routes.ts'
import {
  LoginPage,
  RegisterPage,
  AppPage,
  VerifyAccountPage,
} from '@pages/index.ts'
import {BrowserRouter, Route, Routes} from 'react-router-dom'

const AppRouter = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path={routes.app} element={<AppPage />} />
        <Route path={routes.login} element={<LoginPage />} />
        <Route path={routes.register} element={<RegisterPage />} />
        <Route path={routes.accountVerify} element={<VerifyAccountPage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default AppRouter
