import {Header, SideBar} from '@components/shared/index.ts'
import {HEIGHT} from '@constants/sharedDatas.ts'
import {FC, PropsWithChildren} from 'react'
import {Container} from 'react-bootstrap'

const MAX_HEIGHT = HEIGHT * 0.92

const AppLayout: FC<PropsWithChildren> = ({children}) => {
  return (
    <main>
      <Header />
      <div
        className="d-flex position-relative"
        style={{
          height: `${MAX_HEIGHT}px`,
        }}
      >
        <SideBar />
        <Container
          style={{
            marginLeft: `15%`,
            width: '85%',
          }}
        >
          {children}
        </Container>
      </div>
    </main>
  )
}

export default AppLayout
