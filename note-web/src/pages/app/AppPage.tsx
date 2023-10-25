import {AppLayout} from '@components/layouts/index.ts'
import {FC} from 'react'
import {FormControl, FormLabel, InputGroup} from 'react-bootstrap'

const AppPage: FC = () => {
  return (
    <AppLayout>
      <div className="pt-5 ps-5">
        <div
          style={{
            width: '40%',
          }}
        >
          <FormLabel>Directory Path</FormLabel>
          <InputGroup className="mb-3 ">
            <InputGroup.Text id="basic-addon1">~</InputGroup.Text>
            <FormControl
              value={'/home'}
              style={{
                fontSize: '21px !important',
              }}
            />
          </InputGroup>
        </div>
      </div>
    </AppLayout>
  )
}
export default AppPage
