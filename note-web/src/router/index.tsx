import {AuthRoute} from '@components/shared/index.ts'
import routes from '@constants/routes.ts'
import {
  LoginPage,
  RegisterPage,
  AppPage,
  VerifyAccountPage,
} from '@pages/index.ts'
import {BrowserRouter, Navigate, Route, Routes} from 'react-router-dom'

const AppRouter = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path={routes.root} element={<Navigate to={routes.login} />} />
        <Route
          path={routes.app}
          element={<AuthRoute element={<AppPage />} />}
        />
        <Route path={routes.login} element={<LoginPage />} />
        <Route path={routes.register} element={<RegisterPage />} />
        <Route path={routes.accountVerify} element={<VerifyAccountPage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default AppRouter
