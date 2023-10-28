import {QueryClient} from '@tanstack/react-query'

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      cacheTime: 1000 * 10,
    },
  },
})

export default queryClient
