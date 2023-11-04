import {createNewDirectory} from '@api/mutations/directoryMutations.ts'
import {useAppSelector} from '@hooks/useRedux.ts'
import {selectCurrentDirId} from '@slices/directorySlice.ts'
import {useMutation} from '@tanstack/react-query'
import isOkStatus from '@utils/isOkStatus.ts'
import {AxiosError} from 'axios'
import {useState} from 'react'
import {toast} from 'react-toastify'

const useCreateNewDirectory = (
  runAfterSuccess: (newDirectory: Directory) => void
) => {
  const [fieldError, setFieldError] = useState<string>()
  const {mutateAsync} = useMutation({
    mutationKey: ['createNewDirectory'],
    mutationFn: createNewDirectory,
    onSuccess: (res) => {
      if (!isOkStatus(res.statusCode)) {
        return
      }
      toast.success(res.description)
      runAfterSuccess(res.data)
    },
    onError: (error: AxiosError<ErrorResponse<{error: string}>>) => {
      toast.error(error.response?.data.description)
    },
  })
  const parentDirId = useAppSelector(selectCurrentDirId)

  const create = (name: string) => {
    if (name.trim().length === 0) {
      return setFieldError("Folder'name is required!")
    }
    if (name.length < 1 || name.length > 30) {
      return setFieldError("Folder'name is 1 to 30 characters!")
    }
    setFieldError('')
    mutateAsync({
      name: name,
      parentDirId,
    })
  }

  return {create, fieldError}
}

export default useCreateNewDirectory
