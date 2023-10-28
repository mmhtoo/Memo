import {AppLayout} from '@components/layouts/index.ts'
import {FC} from 'react'
import {FormControl, FormLabel, InputGroup} from 'react-bootstrap'
import DirectoryGroup from './components/DirectoryGroup.tsx'
import {useNavigate, useParams} from 'react-router-dom'
import runOnce from '@utils/runOnce.ts'
import {useAppSelector} from '@hooks/useRedux.ts'
import {selectUserIdAndName} from '@slices/userSlice.ts'
import useGetDirectoriesForUser from './hooks/useGetDirectoriesForUser.ts'

const AppPage: FC = () => {
  const {userId} = useParams<{userId: string}>()
  const {userId: savedUserId} = useAppSelector(selectUserIdAndName)
  const navigate = useNavigate()

  const {
    directoriesWithoutHome,
    isLoading: isLoadingDirs,
    updateDirectories,
  } = useGetDirectoriesForUser()

  const onAddNewDir = (newDir: Directory) => {
    console.log(newDir)
    updateDirectories((prev) => [newDir, ...prev])
  }

  runOnce(() => {
    // for preventing url overwriting
    if (userId != savedUserId) {
      navigate(`/${savedUserId}`, {
        replace: true,
      })
    }
  })

  return (
    <AppLayout>
      <div className="pt-5 ps-5">
        {/* <div
          style={{
            width: '40%',
          }}
        >
          <FormLabel>Directory Path</FormLabel>
          <InputGroup className="mb-3 ">
            <InputGroup.Text id="basic-addon1">~</InputGroup.Text>
            <FormControl
              style={{
                fontSize: '21px !important',
              }}
            />
          </InputGroup>
        </div> */}
        <DirectoryGroup
          directories={directoriesWithoutHome}
          isLoading={isLoadingDirs}
          onAddNewDirectory={onAddNewDir}
          onPress={() => {
            console.log('hello')
          }}
          skeletonCount={30}
        />
      </div>
    </AppLayout>
  )
}
export default AppPage
