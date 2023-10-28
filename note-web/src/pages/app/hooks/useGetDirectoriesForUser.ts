import {getDirectoriesForUser} from '@api/queries/directoryQueries.ts'
import {useAppDispatch} from '@hooks/useRedux.ts'
import {
  setCurrentDir,
  setRootDir,
  setCurrentDirId,
} from '@slices/directorySlice.ts'
import {useQuery} from '@tanstack/react-query'
import {useEffect, useRef, useState} from 'react'

const useGetDirectoriesForUser = () => {
  const {data, isLoading, isError, error} = useQuery({
    queryKey: ['directories'],
    queryFn: getDirectoriesForUser,
  })
  const [directoriesWithoutHome, setDirectoriesWithoutHome] = useState<
    Directory[]
  >([])
  const dispatch = useAppDispatch()
  const currentDirRef = useRef<Directory | undefined>()

  useEffect(() => {
    if (!data || data.data.length == 0) return
    setDirectoriesWithoutHome(
      data.data.filter((directory) => {
        if (directory.parentDirId != null) return true
        currentDirRef!.current = directory
        dispatch(
          setRootDir({
            id: directory.directoryId,
            name: directory.name,
          })
        )
        return false
      })
    )
  }, [data])

  // for getting current directory
  useEffect(() => {
    if (!currentDirRef.current) return
    dispatch(setCurrentDir(currentDirRef.current?.name!))
    dispatch(setCurrentDirId(currentDirRef.current?.directoryId!))
  }, [directoriesWithoutHome])

  return {
    allDirectories: data ? data.data : [],
    directoriesWithoutHome,
    isLoading,
    isError,
    error,
    updateDirectories: setDirectoriesWithoutHome,
  }
}

export default useGetDirectoriesForUser
