import {verifyAccount} from '@api/mutations/accountMutations.ts'
import {yupResolver} from '@hookform/resolvers/yup'
import {useMutation} from '@tanstack/react-query'
import {SubmitHandler, useForm} from 'react-hook-form'
import {useNavigate} from 'react-router-dom'
import {toast} from 'react-toastify'
import * as yup from 'yup'
import {string, InferType} from 'yup'

const schema = yup.object({
  code: string()
    .required('Code is required!')
    .matches(/^\d{5,5}$/, 'Invalid code format!'),
  email: string()
    .required('Email is required!')
    .email('Invalid email format!')
    .min(5, 'Email should have at least 5 characters and 50 characters!')
    .max(50, 'Email should have at least 5 characters and 50 characters!'),
})

type CodeForm = InferType<typeof schema>

const useVerifyAccount = () => {
  const {register, formState, setError, setValue, clearErrors, handleSubmit} =
    useForm<CodeForm>({
      resolver: yupResolver(schema),
      reValidateMode: 'onChange',
    })

  const {mutateAsync, isLoading} = useMutation({
    mutationKey: ['users', 'verify'],
    mutationFn: verifyAccount,
  })

  // const navigate = useNavigate()

  const onSubmit: SubmitHandler<CodeForm> = (data) => {
    clearErrors()
    mutateAsync({
      code: data.code,
      email: data.email,
    })
      .then((res) => {
        toast.success(res.description)
      })
      .catch((e) => {
        const res = e.response?.data as ErrorResponse<
          CodeForm & {error: string}
        >
        Object.keys(res.errors).forEach((key) => {
          if (key == 'error') {
            toast.error(res.errors.error)
            return
          }
          setError(key as keyof CodeForm, {
            message: res.errors[key as keyof typeof res.errors],
          })
        })
      })
  }

  return {
    register,
    errors: formState.errors,
    onSubmit,
    isVerifying: isLoading,
    handleSubmit,
    setValue,
  }
}

export default useVerifyAccount
