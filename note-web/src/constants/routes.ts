// routes for client side
const routes = {
  root: '/',
  login: '/sign-in',
  register: '/sign-up',
  app: ':userId',
  accountVerify: '/verify-account',
}

export default routes

// api routes
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
