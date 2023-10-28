// routes for client side
const routes = {
  login: '/sign-in',
  register: '/sign-up',
  app: '/:userId',
  root : '/',
  accountVerify: '/verify-account',
}

export default routes

// api endpoints
export const api = {
  register: '/accounts/signup',
  login: '/accounts/auth',
  account: '/accounts/{accountId}',
  verifyAccount: '/accounts/verify',
  logout: '/accounts/logout',
  directories: '/directories',
  directory: '/{directoryId}',
  notes: '/notes',
  note: '/{noteId}',
}
