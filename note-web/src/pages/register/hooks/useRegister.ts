import {yupResolver} from '@hookform/resolvers/yup'
import {useMutation} from '@tanstack/react-query'
import {SubmitHandler, useForm} from 'react-hook-form'
import {registerAccount} from '@api/mutations/accountMutations.ts'
import * as yup from 'yup'
import {string, InferType} from 'yup'
import {toast} from 'react-toastify'
import {useNavigate} from 'react-router-dom'
import routes from '@constants/routes.ts'

const schema = yup.object({
  username: string()
    .required('Username is required!')
    .min(
      3,
      'Username should have at least 3 characters and 20 characters at most!'
    )
    .max(
      20,
      'Username should have at least 3 characters and 20 characters at most!'
    ),
  email: string()
    .email('Invalid email format!')
    .min(5, 'Email should have at least 5 characters and 50 characters!')
    .max(50, 'Email should have at least 5 characters and 50 characters!'),
  password: string()
    .min(
      6,
      'Password should have at least 6 characters and 20 characters at most!'
    )
    .max(
      20,
      'Password should have at least 6 characters and 20 characters at most!"'
    ),
})

type RegisterForm = InferType<typeof schema>

const useRegister = () => {
  const {register, handleSubmit, setError, formState, clearErrors, reset} =
    useForm<RegisterForm>({
      resolver: yupResolver(schema),
    })

  const {mutateAsync, isLoading: isRegistering} = useMutation({
    mutationKey: ['users', 'register'],
    mutationFn: registerAccount,
  })

  const navigate = useNavigate()

  const onSubmit: SubmitHandler<RegisterForm> = (registerData) => {
    clearErrors()
    mutateAsync({
      email: registerData.email!,
      username: registerData.username!,
      password: registerData.password!,
    })
      .then((res) => {
        toast.success(res.description)
        console.log(formState)
        setTimeout(() => {
          navigate(`${routes.accountVerify}`, {
            replace: true,
          })
        }, 50)
      })
      .catch((e) => {
        const res = e.response?.data as ErrorResponse<
          RegisterForm & {error: string}
        >
        Object.keys(res.errors).forEach((key) => {
          if (key == 'error') {
            toast.error(res.errors.error)
            return
          }
          setError(key as keyof RegisterForm, {
            message: res.errors[key as keyof typeof res.errors],
          })
        })
      })
  }

  return {
    register,
    errors: formState.errors,
    reset,
    onSubmit,
    handleSubmit,
    isRegistering,
  }
}

export default useRegister
