import {FileIcon} from '@assets/icons/index.ts'
import {FC} from 'react'

type Props = {
  files: Note[]
  isLoading: boolean
  onPress: (note: Note) => void
  onNewFile: () => void
}

const FileGroup: FC<Props> = (props) => {
  return (
    <div className="py-3 mt-3 d-flex flex-row gap-3 flex-wrap w-75">
      {props.files.map((file) => {
        return (
          <File
            {...file}
            onPress={(note) => console.log(note)}
            onDoubleClick={(note) => console.log(note)}
            key={file.noteId}
          />
        )
      })}
      <div
        onDoubleClick={() => console.log('hello')}
        id="new-folder-btn"
        className="cursor-pointer d-flex flex-column align-items-center justify-content-end "
      >
        <FileIcon width={70} height={60} fill={'#17b978'} />
        <label className="mt-2">New File</label>
      </div>
    </div>
  )
}

export default FileGroup

type FileProps = Note & {
  onPress: (note: Note) => void
  onDoubleClick: (note: Note) => void
}

const File: FC<FileProps> = (props) => {
  const {name} = props

  return (
    <div
      onDoubleClick={() => console.log('hello')}
      id="new-folder-btn"
      className="cursor-pointer d-flex flex-column align-items-center justify-content-end "
    >
      <FileIcon width={70} height={60} fill={'#17b978'} />
      <label className="mt-2">{name}</label>
    </div>
  )
}
