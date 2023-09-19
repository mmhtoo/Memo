import {loginAccount} from '@api/mutations/accountMutations.ts'
import routes from '@constants/routes.ts'
import {yupResolver} from '@hookform/resolvers/yup'
import {useAppDispatch} from '@hooks/useRedux.ts'
import {saveToken} from '@slices/userSlice.ts'
import {useMutation} from '@tanstack/react-query'
import {SubmitHandler, useForm} from 'react-hook-form'
import {useNavigate} from 'react-router-dom'
import {toast} from 'react-toastify'
import * as yup from 'yup'
import {string, InferType} from 'yup'

const schema = yup.object({
  email: string().required('Email is required!').email('Invalid email format!'),
  password: string().required('Password is required!'),
})

type LoginForm = InferType<typeof schema>

const useLogin = () => {
  const {register, formState, handleSubmit, setError, clearErrors} =
    useForm<LoginForm>({
      resolver: yupResolver(schema),
    })

  const {mutateAsync, isLoading} = useMutation({
    mutationKey: ['users', 'login'],
    mutationFn: loginAccount,
  })

  const navigate = useNavigate()
  const dispatch = useAppDispatch()

  const onSubmit: SubmitHandler<LoginForm> = (data) => {
    clearErrors()
    mutateAsync(data)
      .then((res) => {
        toast.success(res.data.description)
        const token = res.headers['authorization']
        dispatch(saveToken(token))
        setTimeout(() => {
          navigate(`${routes.app}`)
        }, 100)
      })
      .catch((e) => {
        const errorBody = e.response?.data as ErrorResponse<
          LoginForm & {error: string}
        >
        Object.keys(errorBody.errors).forEach((key) => {
          if (key == 'error') {
            toast.error(errorBody.errors.error)
            return
          }
          setError(key as keyof LoginForm, {
            message: errorBody.errors[key as keyof typeof errorBody.errors],
          })
        })
      })
  }

  return {
    register,
    errors: formState.errors,
    onSubmit,
    isLoading,
    handleSubmit,
  }
}

export default useLogin
