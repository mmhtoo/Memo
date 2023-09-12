import {QueryClientProvider} from '@tanstack/react-query'
import AppRouter from '@router/index.tsx'
import queryClient from '@libs/react-query/reactQuery.ts'
import {ToastContainer} from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'

const App = () => {
  return (
    <QueryClientProvider client={queryClient}>
      <AppRouter />
      <ToastContainer />
    </QueryClientProvider>
  )
}
export default App
