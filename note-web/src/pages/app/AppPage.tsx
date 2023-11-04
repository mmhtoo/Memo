import {AppLayout} from '@components/layouts/index.ts'
import {FC, useState} from 'react'
import DirectoryGroup, {Directory} from './components/DirectoryGroup.tsx'
import {useNavigate, useParams} from 'react-router-dom'
import runOnce from '@utils/runOnce.ts'
import {useAppSelector} from '@hooks/useRedux.ts'
import {selectUserIdAndName} from '@slices/userSlice.ts'
import useGetDirectoriesForUser from './hooks/useGetDirectoriesForUser.ts'
import {Button, Modal} from 'react-bootstrap'
import FadeIn from '@components/animations/FadeIn.tsx'
import useCreateNewDirectory from './hooks/useCreateNewDirectory.ts'
import FolderAddIcon from '@assets/icons/FolderAddIcon.tsx'
import MenuPopup from '@components/shared/MenuPopup.tsx'

const AppPage: FC = () => {
  const {userId} = useParams<{userId: string}>()
  const {userId: savedUserId} = useAppSelector(selectUserIdAndName)
  const navigate = useNavigate()

  const closeModal = () => {
    setModalState((prev) => ({...prev, show: false}))
  }

  const [modalState, setModalState] = useState({
    show: false,
    title: 'Warning!',
    body: 'Are you sure to cancel for creating new folder?',
    modalFooter: (
      <>
        <Button onClick={closeModal} variant={'secodary'}>
          No
        </Button>
        <Button
          onClick={() => {
            setShowAddNew(false)
            setModalState((prev) => ({...prev, show: false}))
          }}
          variant={'warning'}
        >
          Yes
        </Button>
      </>
    ),
  })
  const [showAddNew, setShowAddNew] = useState(false)
  const {
    directoriesWithoutHome,
    isLoading: isLoadingDirs,
    updateDirectories,
  } = useGetDirectoriesForUser()
  const [menuPopupState, setMenuPopupState] = useState({
    show: false,
    offsetX: 0,
    offsetY: 0,
  })

  const onAddNewDir = (newDir: Directory) => {
    updateDirectories((prev) => [newDir, ...prev])
  }

  const {create, fieldError} = useCreateNewDirectory((newDir) => {
    setShowAddNew(false)
    onAddNewDir(newDir)
  })

  runOnce(() => {
    // for preventing url overwriting
    if (userId != savedUserId) {
      navigate(`/${savedUserId}`, {
        replace: true,
      })
    }
  })

  runOnce(() => {
    const mainPanel = document.getElementById('main-panel')
    if (!mainPanel) return
    const handleDocumentRightClick = (e: MouseEvent) => e.preventDefault()
    document.addEventListener('contextmenu', handleDocumentRightClick)
    // for right click
    const handleMainPanelRightClick = (e: MouseEvent) => {
      e.preventDefault()
      console.log(e)
      setMenuPopupState({
        show: true,
        offsetX: e.x,
        offsetY: e.y,
      })
    }
    mainPanel.addEventListener('contextmenu', handleMainPanelRightClick)
    // for left click
    const handleMainPanelLeftClick = (_e: MouseEvent) => {
      setMenuPopupState((prev) => ({...prev, show: false}))
    }
    document.addEventListener('click', handleMainPanelLeftClick)
    return () => {
      document.removeEventListener('contextmenu', handleDocumentRightClick)
      mainPanel.removeEventListener('contextmenu', handleMainPanelRightClick)
      document.removeEventListener('click', handleMainPanelLeftClick)
    }
  })

  return (
    <AppLayout>
      <div
        id="main-panel"
        style={{
          minWidth: '100%',
          minHeight: '100%',
        }}
        className="pt-5 ps-5"
      >
        <div
          className="py-3 mt-3 d-flex flex-row gap-3 flex-wrap"
          style={{
            width: '95%',
          }}
        >
          {showAddNew && !isLoadingDirs && (
            <FadeIn>
              <Directory
                editable={true}
                errorMessage={fieldError}
                onEndEditing={create}
                showModal={(title, body) => {
                  setModalState((prev) => ({
                    ...prev,
                    title,
                    body,
                    show: true,
                  }))
                }}
                onCancelNewDir={() => setShowAddNew(false)}
              />
            </FadeIn>
          )}
          <DirectoryGroup
            directories={directoriesWithoutHome}
            isLoading={isLoadingDirs}
            onAddNewDirectory={onAddNewDir}
            onPress={() => {
              console.log('hello')
            }}
            skeletonCount={40}
            showModal={(title, body) => {
              setModalState((prev) => ({...prev, title, body, show: true}))
            }}
          />
          {!isLoadingDirs && directoriesWithoutHome.length == 0 && (
            <div
              onClick={() => setShowAddNew(true)}
              id="new-folder-btn"
              className="cursor-pointer d-flex flex-column align-items-center"
            >
              <FolderAddIcon width={80} height={80} fill={'#17b978'} />
              <label>New Folder</label>
            </div>
          )}
        </div>
      </div>
      <Modal backdrop={'static'} show={modalState.show}>
        <Modal.Header>
          <Modal.Title>
            <span className="text-warning">{modalState.title}</span>
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>{modalState.body}</Modal.Body>
        <Modal.Footer>{modalState.modalFooter}</Modal.Footer>
      </Modal>
      <MenuPopup
        show={menuPopupState.show}
        position={{
          offsetX: menuPopupState.offsetX,
          offsetY: menuPopupState.offsetY,
        }}
        onClickNewFile={() => {
          console.log('new file was taken')
        }}
        onClickNewFolder={() => {
          setShowAddNew(true)
          setMenuPopupState((prev) => ({...prev, show: false}))
        }}
      />
    </AppLayout>
  )
}
export default AppPage
