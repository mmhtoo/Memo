import {FC} from 'react'
import {Link} from 'react-router-dom'

const LoginPage: FC = () => {
  return (
    <>
      <h1>login page</h1>
      <Link to={'/hello'}>
        <span>App</span>
      </Link>
    </>
  )
}

export default LoginPage
