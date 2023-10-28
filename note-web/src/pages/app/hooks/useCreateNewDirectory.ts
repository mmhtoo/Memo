import {createNewDirectory} from '@api/mutations/directoryMutations.ts'
import {useAppSelector} from '@hooks/useRedux.ts'
import {selectCurrentDirId} from '@slices/directorySlice.ts'
import {useMutation} from '@tanstack/react-query'
import isOkStatus from '@utils/isOkStatus.ts'
import {toast} from 'react-toastify'

const useCreateNewDirectory = (
  runAfterSuccess: (newDirectory: Directory) => void
) => {
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
    onError: (error) => {
      toast.error(error as any)
    },
  })
  const parentDirId = useAppSelector(selectCurrentDirId)

  const create = (name: string) => {
    if (!name || name.length < 1 || name.length > 30) return
    mutateAsync({
      name: name,
      parentDirId,
    })
  }

  return {create}
}

export default useCreateNewDirectory
