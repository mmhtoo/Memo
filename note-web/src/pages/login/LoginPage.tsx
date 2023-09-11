import routes from '@constants/routes.ts'
import defineTitle from '@utils/defineTitle.ts'
import runOnce from '@utils/runOnce.ts'
import {FC} from 'react'
import {Button, Col, Container, Form, Row} from 'react-bootstrap'
import {Link} from 'react-router-dom'
import {FadeIn} from '@components/animations/index.ts'

const LoginPage: FC = () => {
  runOnce(() => {
    defineTitle('Memo | Sign Up')
  })

  return (
    <Container>
      <FadeIn>
        <Row
          style={{
            display: 'flex',
            justifyContent: 'center',
            flexWrap: 'wrap',
            padding: '180px 8px',
          }}
        >
          <Col
            xs={10}
            sm={7}
            md={8}
            lg={6}
            xl={4}
            style={{
              backgroundColor: 'var(--snow)',
              borderRadius: 4,
              boxShadow: '0 0 4px var(--fade-dark)',
              padding: '32px 16px',
            }}
          >
            <Container>
              <h3 className="text-center" style={{color: 'var(--green)'}}>
                Sign In
              </h3>
              <p
                className="text-center"
                style={{fontSize: '12px', color: 'var(--pale-dark)'}}
              >
                Save you notes with us, sign in your account!
              </p>
            </Container>
            <Form className="px-2 mt-3">
              <Form.Group className="mb-2">
                <Form.Label>Email</Form.Label>
                <Form.Control
                  type={'email'}
                  placeholder={'Enter email address'}
                />
              </Form.Group>
              <Form.Group className="mb-2">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type={'password'}
                  placeholder={'Enter password'}
                />
              </Form.Group>
              <Button className="w-100 mt-2">Sign Up</Button>
              <Container>
                <p
                  className="mt-2 text-center"
                  style={{fontSize: '12px', color: 'var(--pale-dark)'}}
                >
                  New user?
                  <Link
                    to={routes.register}
                    className="ms-1 text-decoration-none"
                  >
                    Sign in
                  </Link>
                </p>
              </Container>
            </Form>
          </Col>
        </Row>
      </FadeIn>
    </Container>
  )
}

export default LoginPage
